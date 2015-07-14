package sample;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import sample.search.SearchCriteria;
import sample.search.Term;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // this converter is intended to align when there is a difference between server bean and client bean
    // not used in this test !!!
    private AbstractBeanConverter converter;

    @FXML
    Text txt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initUser();
    }

    public void initUser() {
        CustomObjectMapper com = new CustomObjectMapper();
        com.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        com.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        String body = loadUser();
        try {
            SearchCriteria u = SearchCriteria.of().likeBegin("code", "Po");
            u = com.readValue(body, SearchCriteria.class);
            Optional<Term> t = u.getTerms().stream().findFirst();
            txt.setText("is read TERM {id:" + t.get().getIdentifier() + ", value:" + t.get().getValue() + ", like:" + t.get().getType() + "}");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadUser() {
        return "{\"first\":0,\"max\":30,\"terms\":[{\"identifier\":\"code\",\"value\":\"P\",\"type\":\"Like\",\"likeType\":\"BEGIN\",\"operator\":true}],\"relations\":[],\"values\":{}}";
    }

}

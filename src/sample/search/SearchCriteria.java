package sample.search;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chardonnereau on 18.03.2015.
 */
public class SearchCriteria {

    private final static int DEFAUTL_MAX = -1;
    private final static int DEFAUTL_FIRST = 0;
    int first, max;
    private List<Term> terms = new LinkedList<>();
    private List<Relation> relations = new LinkedList<>();
    private HashMap<String, String> values = new HashMap<>();

    private SearchCriteria() {
    }

    private SearchCriteria(int first, int max) {
        this.first = first;
        this.max = max;
    }

    public static SearchCriteria of() {
        return new SearchCriteria(DEFAUTL_FIRST, DEFAUTL_MAX);
    }

    public static SearchCriteria of(int max) {
        return new SearchCriteria(DEFAUTL_FIRST, max);
    }

    public static SearchCriteria of(String... fetchs) {
        return SearchCriteria.of().fetchs(fetchs);
    }


    public SearchCriteria like(String identifier, Object value) {
        this.terms.add(SearchElementFactory.like(identifier, value, Like.LikeType.CONTAINS));
        return this;
    }

    public SearchCriteria likeBegin(String identifier, String value) {
        this.terms.add(SearchElementFactory.like(identifier, value, Like.LikeType.BEGIN));
        return this;
    }

    public SearchCriteria like(String identifier, String value, Like.LikeType likeType, boolean negation) {
        this.terms.add(new Like(identifier, value, TermType.LIKE, likeType, negation));
        return this;
    }

    public SearchCriteria join(String parent, Term... terms) {
        this.relations.add(SearchElementFactory.join(parent, terms));
        return this;
    }

    public SearchCriteria join(String parent, Join subJoin) {
        this.relations.add(SearchElementFactory.join(parent, subJoin));
        return this;
    }

    public SearchCriteria join(String parent, List<Term> terms) {
        this.relations.add(SearchElementFactory.join(parent, terms));
        return this;
    }

    private SearchCriteria fetchs(String[] fetchs) {
        for (String fetch : fetchs) {
            this.relations.add(SearchElementFactory.fetch(fetch));
        }
        return this;
    }

    public SearchCriteria fetch(String parent, List<Term> terms) {
        this.relations.add(SearchElementFactory.fetch(parent, terms));
        return this;
    }

    public SearchCriteria eq(String identifier, Object value) {
        this.terms.add(SearchElementFactory.eq(identifier, value));
        return this;
    }

    public SearchCriteria greater(String identifier, Object value) {
        this.terms.add(SearchElementFactory.greater(identifier, value));
        return this;
    }

    public SearchCriteria less(String identifier, Object value) {
        this.terms.add(SearchElementFactory.less(identifier, value));
        return this;
    }

    public SearchCriteria in(String identifier, List<String> status) {
        this.terms.add(SearchElementFactory.in(identifier, status));
        return this;
    }

    public SearchCriteria or(Term... terms) {
        this.terms.add(SearchElementFactory.or(terms));
        return this;
    }

    public SearchCriteria or(List<Term> terms) {
        this.terms.add(SearchElementFactory.or(terms));
        return this;
    }

    public SearchCriteria and(Term... terms) {
        this.terms.add(SearchElementFactory.and(terms));
        return this;
    }

    public SearchCriteria and(List<Term> terms) {
        this.terms.add(SearchElementFactory.and(terms));
        return this;
    }

    public SearchCriteria addTerm(Term term) {
        if (term != null) {
            this.terms.add(term);
        }
        return this;
    }

    public void setItem(String key, String value) {
        this.values.put(key, value);
    }

    public String getItem(String key) {
        return this.values.get(key);
    }

    // getter setter

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<Relation> getRelations() {
        return Collections.unmodifiableList(relations);
    }

    public List<Term> getTerms() {
        return Collections.unmodifiableList(terms);
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

}

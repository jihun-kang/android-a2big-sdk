package com.a2big.android.library.network.json.model;

/**
 * Created by a2big on 15. 6. 22..
 */
public class ModelContainer {
    ClassModel results;
    String errors;

    public ClassModel getResults() {
        return results;
    }

    public String getErrors() {
        return errors;
    }

    public void setResults(ClassModel results) {
        this.results = results;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}

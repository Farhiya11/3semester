package domain;

import domain.BatchController;

public class Batch {

    private float id;
    private BatchController batchController;

    public Batch() {

    }

    public Batch(float id) {
        this.id = id;
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }
}

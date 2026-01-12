package org.noa.hospitaleo.repository;

// Initialization-on-demand-holder idiom
public class RepositoryFactory {

    private RepositoryFactory() {}

    public static class Holder
    {
        private Holder(){}
        private static final MockEntityRepository repository = new MockEntityRepository();
    }

    public static MockEntityRepository getInstance() {
        return Holder.repository;
    }
}

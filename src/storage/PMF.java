package storage;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * This class allows to get PersistenceManagerFactory from any place.
 * And this factory produces PersistenceManager for performing database
 * operations. After performing all operations needed you should close
 * PersistenceManager for applying changes.
 * @author Sergey
 *
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}

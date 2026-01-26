package org.noa.hospitaleo.backend.utils;

import java.sql.SQLException;
import java.util.concurrent.Future;

public class FutureUtils {

    public static void waitForFuture(Future<?> future) throws SQLException {
        try {
            future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ExceptionUtils.handleDatabaseThreadException(e);
        } catch (Exception e) {
            ExceptionUtils.handleDatabaseThreadException(e);
        }
    }

    public static  <T> T waitForFutureWithResult(Future<T> future) throws SQLException {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            ExceptionUtils.handleDatabaseThreadException(e);
            return null;
        } catch (Exception e) {
            ExceptionUtils.handleDatabaseThreadException(e);
            return null;
        }
    }
}

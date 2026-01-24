package org.noa.hospitaleo.backend;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.noa.hospitaleo.HospitalEoApplication;
import org.noa.hospitaleo.backend.routes.BackupRoutes;
import java.sql.SQLException;

public class BackupManager {

    Timeline timeline;

    public  BackupManager()
    {
         timeline = new Timeline(
                new KeyFrame(Duration.minutes(5), e -> Thread.startVirtualThread(this::doBackup))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void startManaging()
    {
        timeline.play();
    }

    private void  doBackup()
    {
        try {
            BackupRoutes.dropBackupTable(HospitalEoApplication.getApi().getConnection());
            BackupRoutes.createBackup(HospitalEoApplication.getApi().getConnection());
        } catch (SQLException e) {
            HospitalEoApplication.logger.error("Greska pri stvaranju backup",e);
        }
        HospitalEoApplication.logger.info("Backup uspjesno stvoren");

    }

}

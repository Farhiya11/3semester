package domain;

import domain.command.BatchController;
import domain.command.CommandController;
import java.lang.Thread;

public class Main {

    public static void main(String[] args) {
        BatchController batch = new BatchController();
        CommandController cmd = new CommandController();
        float amount = 65535;

        try {
            cmd.clear();
            cmd.reset();
            Thread.sleep(8000);
            batch.setProductType(0);
            cmd.setSpeed(600);
            batch.setBatchId(2);
            batch.setBatchAmount(amount);
            Thread.sleep(2000);
            cmd.start();
            Thread.sleep(2000);
            batch.getBatchAmount();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

}

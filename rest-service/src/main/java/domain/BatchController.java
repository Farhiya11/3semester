package domain;

import domain.MachineConnection;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public class BatchController {

    private MachineConnection machineConnection;
    public float amount;

    public BatchController() {
        this.machineConnection = new MachineConnection("127.0.0.1", 4840);
        this.machineConnection.connect();
    }

    public void getBatchAmount() {
        float amount = this.amount;
        try {

            while(true) {
                NodeId nodeId5 = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");
                DataValue dataValue = machineConnection.getClient().readValue(0, TimestampsToReturn.Both, nodeId5)
                        .get();

                Variant variant = dataValue.getValue();

                int value = (int)variant.getValue();

                System.out.println("Produced: " + value);
                Thread.sleep(300);
                if (value >= 500) {
                    // stop();
                    break;
                }

            }

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        //return amount;
    }

    public void setBatchAmount(float amount) {
        try {

            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.Parameter[2].Value");
            machineConnection.getClient().writeValue(nodeId, DataValue.valueOnly(new Variant(amount))).get();
            System.out.println("domain.Write, Set product amount: " + amount);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public float getBatchId() {
        float value = 0;
        try {

            while(true) {
                NodeId batchId = new NodeId(6, "::Program:Cube.Status.Parameter[0].Value");
                DataValue dataValue = machineConnection.getClient().readValue(0, TimestampsToReturn.Both, batchId)
                        .get();

                Variant variant = dataValue.getValue();

                value =  (float) variant.getValue();

                System.out.println("Batch ID: " + value);
                return value;
            }

        } catch (Throwable ex) {
            ex.printStackTrace();
            return value;
        }
    }

    public void setBatchId(float id) {
        try {

            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.Parameter[0].Value");
            machineConnection.getClient().writeValue(nodeId, DataValue.valueOnly(new Variant(id))).get();
            System.out.println("domain.write, set batch ID to: " + id);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public int getProductType() {
        return 0;
    }

    public void setProductType(float id) {
        try {

            NodeId nodeId5 = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
            machineConnection.getClient().writeValue(nodeId5, DataValue.valueOnly(new Variant(id))).get();
            System.out.println("domain.Write: Set product type: " + id);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}

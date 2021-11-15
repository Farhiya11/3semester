package domain;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

import java.util.*;
import java.lang.*;



public class Write {

    private static MachineConnection machineConnection = new MachineConnection();

    public static void main(String[] args) {
        try {
            clear();
            reset();
            Thread.sleep(8000);
            setProductType(0);
            setSpeed(600);
            //setBatchId(1);
            setBatchAmount(500);
            Thread.sleep(2000);
            start();
            Thread.sleep(2000);
            getProduced();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getProduced() {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            while(true) {
                NodeId nodeId5 = new NodeId(6, "::Program:Cube.Admin.ProdProcessedCount");
                DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId5)
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
    }

    public static void setBatchAmount(float amount) {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.Parameter[2].Value");
            client.writeValue(nodeId, DataValue.valueOnly(new Variant(amount))).get();
            System.out.println("domain.Write, Set product amount: " + amount);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setBatchId(float id) {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId = new NodeId(6, "::Program:Cube.Command.Parameter[0].Value");
            client.writeValue(nodeId, DataValue.valueOnly(new Variant(id))).get();
            System.out.println("domain.write, set batch ID to: " + id);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setProductType(float Id) {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId5 = new NodeId(6, "::Program:Cube.Command.Parameter[1].Value");
            client.writeValue(nodeId5, DataValue.valueOnly(new Variant(Id))).get();
            System.out.println("domain.Write: Set product type: " + Id);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void clear() {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId5 = NodeId.parse("ns=6;s=::Program:Cube.Command.CntrlCmd");
            client.writeValue(nodeId5, DataValue.valueOnly(new Variant(5))).get();
            System.out.println("domain.Write: CLEAR");

            NodeId nodeId6 = NodeId.parse("ns=6;s=::Program:Cube.Command.CmdChangeRequest");
            client.writeValue(nodeId6, DataValue.valueOnly(new Variant(true))).get();
            System.out.println("domain.Write: CmdChangeRequest: true");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void reset() {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId5 = NodeId.parse("ns=6;s=::Program:Cube.Command.CntrlCmd");
            client.writeValue(nodeId5, DataValue.valueOnly(new Variant(1))).get();
            System.out.println("domain.Write: RESET");

            NodeId nodeId6 = NodeId.parse("ns=6;s=::Program:Cube.Command.CmdChangeRequest");
            client.writeValue(nodeId6, DataValue.valueOnly(new Variant(true))).get();
            System.out.println("domain.Write: CmdChangeRequest: true");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void start() {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId5 = NodeId.parse("ns=6;s=::Program:Cube.Command.CntrlCmd");
            client.writeValue(nodeId5, DataValue.valueOnly(new Variant(2))).get();
            System.out.println("domain.Write: START");

            NodeId nodeId6 = NodeId.parse("ns=6;s=::Program:Cube.Command.CmdChangeRequest");
            client.writeValue(nodeId6, DataValue.valueOnly(new Variant(true))).get();
            System.out.println("domain.Write: CmdChangeRequest: true");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void setSpeed(float speed) {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId5 = new NodeId(6, "::Program:Cube.Command.MachSpeed");
            client.writeValue(nodeId5, DataValue.valueOnly(new Variant((float) speed))).get();
            System.out.println("domain.Write: Speed: " + speed);

        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public static void stop() {
        try {
            machineConnection.connect();
            OpcUaClient client = machineConnection.getClient();

            NodeId nodeId5 = NodeId.parse("ns=6;s=::Program:Cube.Command.CntrlCmd");
            client.writeValue(nodeId5, DataValue.valueOnly(new Variant(3))).get();
            System.out.println("domain.Write: STOP");

            NodeId nodeId6 = NodeId.parse("ns=6;s=::Program:Cube.Command.CmdChangeRequest");
            client.writeValue(nodeId6, DataValue.valueOnly(new Variant(true))).get();
            System.out.println("domain.Write: CmdChangeRequest: true");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}

package domain;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.MachineConnection;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

/**
 *
 * @author athil
 */
public class Read {
    public static void main(String[] args) {
            try 
            {
                MachineConnection machineConnection = new MachineConnection();
                machineConnection.connect();
                OpcUaClient client = machineConnection.getClient();

                NodeId nodeId = NodeId.parse("ns=6;s=::Program:Cube.Status.MachSpeed");

                DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId)
                        .get();
                System.out.println("DataValue= " + dataValue);

                Variant variant = dataValue.getValue();
                
                System.out.println("Variant= " + variant);

                float random = (float)variant.getValue();
                System.out.println("myVariable= " + random);

            }
            catch(Throwable ex)
            {
                ex.printStackTrace();
            }

    }

}

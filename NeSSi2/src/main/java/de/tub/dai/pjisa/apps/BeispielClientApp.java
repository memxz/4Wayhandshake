/**
 * Copyright 2012 DAI-Labor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.tub.dai.pjisa.apps;


import de.dailab.nessi.core.api.annotations.Category;
import de.dailab.nessi.core.api.annotations.Description;
import de.dailab.nessi.core.api.annotations.IntegerField;
import de.dailab.nessi.core.api.annotations.Name;
import de.dailab.nessi.core.api.events.Event;
import de.dailab.nessi.core.api.events.TickedEvent;
import de.dailab.nessi.hibernate.mapping.rec.HbmRecordingInt;
import de.dailab.nessi.hibernate.util.EventFactory;
import de.dailab.nessi.ip.api.address.IPv4Address;
import de.dailab.nessi.ip.api.applications.CategoryLabels;
import de.dailab.nessi.ip.api.layer.ILayerHandler;
import de.dailab.nessi.ip.application.AbstractIPApplication;
import de.dailab.nessi.ip.handler.IPNetworkMetaInf;
import de.dailab.nessi.ip.ipmodel.IPDevice;
import de.dailab.nessi.recording.repository.IRecorder;

/**
 * Nichts sinnvolles hier im Moment, wird in LV ausgefuellt.
 * 
 * @author bsufka
 * 
 */
@Name("Beispiel Client")
@Category(values = { CategoryLabels.BASIC_COMMUNICATION, CategoryLabels.CLIENT })
public class BeispielClientApp extends AbstractIPApplication {

	@IntegerField("Beispiel Echo Server Port")
	@Description("Echo Server Port number")
	protected int echoServerPort = 7;

	// define another integer variable that can be configured via NeSSi GUI
	@IntegerField("Beispiel Client Port")
	@Description("Port for the Echo Client")
	protected int echoClientPort = 8;

	ILayerHandler handler;

	@Override
	public boolean start() {
		handler = getLayerHandler();

		// first we have to register a port where we can receive answers
		handler.getTransportLayer().registerUDPPort(echoClientPort, this);

		// we need to register ourselves for the tick
		// where we want to send our first message
		handler.addEvent(new TickedEvent(this, 10));

		return super.start();
	}

	@Override
	public void handleEvent(Event event) {
		if (!(event instanceof TickedEvent)) {
			return; // we only handle ticked events
		}

		TickedEvent tickedEvent = (TickedEvent) event;
		IPNetworkMetaInf metaInf = (IPNetworkMetaInf) handler
				.getNetworkMetaInf();
		IPv4Address addr;
		// we try to get an IP for an echo-server somewhere in the network
		addr = metaInf
				.getRandomIPv4AddressForApplication(BeispielClientApp.class);

		if (addr != null) {
			// a server was found so we can create a UDP-Packet and send it
			byte[] message = "Hello World".getBytes();
			handler.getTransportLayer().sendUDPPacket(echoClientPort,
					echoServerPort, addr, message);
			// System.out.println("Client Sent message: '" + new String(message)
			// +
			// "'");

			// at this point we want to record an event to the database
			// that we sent the echo-message successfully

			int tick = handler.getCurrentTick();
			IPDevice device = (IPDevice) handler.getDevice();

			HbmRecordingInt eventRec = EventFactory.getIntegerEvent(tick,
					device, EventLabels.ECHO_REQ_SENT, 1);

			IRecorder recorder = getRecorder(EventLabels.class);
			recorder.record(eventRec);
		}
		// we register ourself for the next tick where
		// we want to send an echo-message
		tickedEvent.setTick(tickedEvent.getTick() + 10);
		handler.addEvent(tickedEvent);
	}
}

package org.alexdev.kepler.messages.outgoing.events;

import org.alexdev.kepler.game.events.Event;
import org.alexdev.kepler.messages.types.MessageComposer;
import org.alexdev.kepler.server.netty.streams.NettyResponse;

import java.util.List;

public class ROOMEVENT_LIST extends MessageComposer {
    private final List<Event> events;
    private final int typeId;

    public ROOMEVENT_LIST(int typeId, List<Event> events) {
        this.typeId = typeId;
        this.events = events;
    }

    @Override
    public void compose(NettyResponse response) {
        response.writeInt(this.typeId);
        response.writeInt(this.events.size());

        for (Event event : this.events) {
            response.writeString(event.getRoomId());
            response.writeString(event.getEventHoster().getName());
            response.writeString(event.getName());
            response.writeString(event.getDescription());
            response.writeString(event.getStartedDate());
        }
    }

    @Override
    public short getHeader() {
        return 369; // "Eq"
    }
}

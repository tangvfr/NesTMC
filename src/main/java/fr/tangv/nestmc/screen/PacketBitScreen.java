package fr.tangv.nestmc.screen;

public class PacketBitScreen extends PAcketplayout{
	
	 public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
		    paramPacketDataSerializer.b(this.a);
		    paramPacketDataSerializer.writeByte(this.b);
		    paramPacketDataSerializer.b(this.c.length);
		    for (MapIcon mapIcon : this.c) {
		      paramPacketDataSerializer.writeByte((mapIcon.getType() & 0xF) << 4 | mapIcon.getRotation() & 0xF);
		      paramPacketDataSerializer.writeByte(mapIcon.getX());
		      paramPacketDataSerializer.writeByte(mapIcon.getY());
		    } 
		    paramPacketDataSerializer.writeByte(this.f);
		    if (this.f > 0) {
		      paramPacketDataSerializer.writeByte(this.g);
		      paramPacketDataSerializer.writeByte(this.d);
		      paramPacketDataSerializer.writeByte(this.e);
		      paramPacketDataSerializer.a(this.h);
		    } 
		  }

}
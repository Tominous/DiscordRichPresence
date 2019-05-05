package de.erdbeerbaerlp.discordrpc;

import java.io.File;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.Level;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class Message_Message implements IMessage {
	  // A default constructor is always required
	  public Message_Message(){}

	  private String toSend;
	  public Message_Message(String toSend) {
	    this.toSend = toSend;
	  }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
		  
		  ByteBufUtils.writeUTF8String(buf, toSend);
		  buf.setByte(1, 0);
		  System.out.println();
		  
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	    
		  toSend = buf.toString(StandardCharsets.UTF_8).trim();
	  }
	  public static class MSGReceiveHandler implements IMessageHandler<Message_Message, IMessage> {
		  // Do note that the default constructor is required, but implicitly defined in this case

		  @Override
		  public IMessage onMessage(Message_Message message, MessageContext ctx) {
		    DRPCLog.Info("Message-packet received: \""+message.toSend+"\"! Applying message...");
		    DRPCEventHandler.serverCustomMessage = message.toSend;
		    return null;
		  }
		}
	}
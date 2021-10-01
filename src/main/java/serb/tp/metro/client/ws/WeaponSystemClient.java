package serb.tp.metro.client.ws;

import org.lwjgl.input.Mouse;

import serb.tp.metro.common.ws.WeaponSystem;

public class WeaponSystemClient extends WeaponSystem{

	
	public boolean mouseLeftButtonPressed() {
		return Mouse.isButtonDown(0);
	}
	
	public boolean mouseRightButtonPressed() {
		return Mouse.isButtonDown(1);
	}
}

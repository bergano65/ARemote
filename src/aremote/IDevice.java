/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aremote;

import com.android.chimpchat.core.PhysicalButton;
import java.awt.image.BufferedImage;

/**
 *
 * @author Administrator
 */
public interface IDevice {

BufferedImage getScreenShot();    
int getWidth();
int getHeight();
void touch(int x, int y);
void hold(int x, int y, int ms);
void press(PhysicalButton b);

}

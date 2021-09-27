package aremote;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.IChimpImage;
import com.android.chimpchat.core.TouchPressType;
import java.awt.image.BufferedImage;
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class AdbDevice implements IDevice {
   
    private ChimpChat _chimpChat;
    
    private IChimpDevice _device;
    
    public AdbDevice()
    {
        try
        {
            _chimpChat = ChimpChat.getInstance();      
            _device = _chimpChat.waitForConnection();
            Collection<String> pr = _device.getPropertyList();
        }
        catch (Throwable e)
        {
            
        }
    }
    
   public BufferedImage GetScreenShot()
   {
       if (_device == null)
       {
           return null;
       }
       
       IChimpImage img =_device.takeSnapshot();
       return img.getBufferedImage();
   }
}

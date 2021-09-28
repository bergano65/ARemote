package aremote;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.IChimpImage;
import com.android.chimpchat.core.TouchPressType;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private int _width = -1;

    private int _height = -1;

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
    
   public BufferedImage getScreenShot()
   {
       if (_device == null)
       {
           return null;
       }
       
       IChimpImage img =_device.takeSnapshot();
       
       if (_width == -1)
       {
          _width = img.getBufferedImage().getWidth();
            _height = img.getBufferedImage().getHeight();
     }
       return img.getBufferedImage();
   }


   public int getWidth()
   {
       return _width;
   }
   
   public int getHeight()
   {
       return _height;
    }
   
   public void touch(int x, int y)
   {
       if (_device == null)
       {
           return;
       }
       
        try {
            _device.touch(y, y, TouchPressType.DOWN);
            Thread.sleep(500);
                _device.touch(y, y, TouchPressType.UP);
   
        } catch (InterruptedException ex) {
            Logger.getLogger(AdbDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
   }


        }

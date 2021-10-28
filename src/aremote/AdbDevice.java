package aremote;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.adb.AdbChimpDevice;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.IChimpImage;
import com.android.chimpchat.core.PhysicalButton;
import com.android.chimpchat.core.TouchPressType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
            _device = GetDevice();
            if (_device != null)
            {
                    Collection<String> pr = _device.getPropertyList();
            }
        }
        catch (Throwable e)
        {
            
        }
    }
    
   private IChimpDevice GetDevice()
   {
       try
       {
            if (_device == null)
            {
                _device = _chimpChat.waitForConnection();
/*                _device = _chimpChat.waitForConnection(100000, "emulator-5554"); */
            }
       }
       catch (Throwable e)
       {
           System.out.print(e.getMessage());
       }   
       
       
       return _device;
   }
   
   public BufferedImage getScreenShot()
   {
       _device = GetDevice();
       if (_device == null)
       {
           return null;
       }
       
       IChimpImage img =_device.takeSnapshot();
       BufferedImage outImg = img.getBufferedImage();

       
       if (_width == -1)
       {
          _width = outImg.getWidth();
            _height = outImg.getHeight();

//                img.writeToFile("d:\\i\\image.png", "png");
            
       }
       
      return outImg;
   }



   public int getWidth()
   {
       return _width;
   }
   
   public int getHeight()
   {
       return _height;
    }
   

   public void hold (int x, int y, int ms)
   {
       _device = GetDevice();
       if (_device == null)
       {
           return;
       }
       
        try {
            _device.touch(x, y, TouchPressType.DOWN);
            Thread.sleep(ms);
                _device.touch(x, y, TouchPressType.UP);
   
        } catch (InterruptedException ex) {
            Logger.getLogger(AdbDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
    
   }
   
  
   public void touch(int x, int y)
   {
       _device = GetDevice();
       if (_device == null)
       {
           return;
       }
       
        try {
            _device.touch(x, y, TouchPressType.DOWN);
            Thread.sleep(100);
                _device.touch(x, y, TouchPressType.UP);
   
        } catch (InterruptedException ex) {
            Logger.getLogger(AdbDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

      public void press(PhysicalButton b)
   {
      _device = GetDevice();
      if (_device == null)
       {
           return;
       }

       _device.press(b, TouchPressType.DOWN_AND_UP);

   }   
      
      public  void shell(String cmd)
      {
_device.shell(cmd);
        
    }

    @Override
    public void type(String s) {
        _device.shell("input text " + s);
    }
        }

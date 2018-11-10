package main.managers.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.managers.GameManager;


public class AudioManager{
	GameManager game_manager;
	AudioFormat loop_format;
    DataLine.Info loop_info;
    Clip loop_clip;
    File loop_music;
    AudioInputStream loop_stream;
    
    
    AudioInputStream fly_stream;
    File fly_music;
    AudioFormat fly_format;
    DataLine.Info fly_info;
    Clip fly_clip;
    
    AudioInputStream hit_stream;
    File hit_music;
    AudioFormat hit_format;
    DataLine.Info hit_info;
    Clip hit_clip;
    
    AudioInputStream catch_stream;
    File catch_music;
    AudioFormat catch_format;
    DataLine.Info catch_info;
    Clip catch_clip;
    
    AudioInputStream win_stream;
    File win_music;
    AudioFormat win_format;
    DataLine.Info win_info;
    Clip win_clip;
    
    AudioInputStream go_stream;
    File go_music;
    AudioFormat go_format;
    DataLine.Info go_info;
    Clip go_clip;
    
    AudioInputStream shoot_stream;
    File shoot_music;
    AudioFormat shoot_format;
    DataLine.Info shoot_info;
    Clip shoot_clip;
    
    AudioInputStream button_stream;
    File button_music;
    AudioFormat button_format;
    DataLine.Info button_info;
    Clip button_clip;
    
    AudioInputStream enter_stream;
    File enter_music;
    AudioFormat enter_format;
    DataLine.Info enter_info;
    Clip enter_clip;
    
    AudioInputStream menu_stream;
    File menu_music;
    AudioFormat menu_format;
    DataLine.Info menu_info;
    Clip menu_clip;

	public AudioManager(GameManager game_manager) {
		super();
		this.game_manager = game_manager;
	}
	public AudioManager() {
		super();
	}
	
	public void initMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		 
	    loop_stream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("background.wav"));
	    loop_format = loop_stream.getFormat();
	    loop_info = new DataLine.Info(Clip.class, loop_format);
	    loop_clip = (Clip) AudioSystem.getLine(loop_info);
	    loop_clip.open(loop_stream); 
	   
	    fly_stream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("fly.wav"));
	    fly_format = fly_stream.getFormat();
	    fly_info = new DataLine.Info(Clip.class, fly_format);
	    fly_clip = (Clip) AudioSystem.getLine(fly_info);
	    fly_clip.open(fly_stream);
	    
	    shoot_stream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("shoot.wav"));
	    shoot_format = shoot_stream.getFormat();
	    shoot_info = new DataLine.Info(Clip.class, shoot_format);
	    shoot_clip = (Clip) AudioSystem.getLine(shoot_info);
	    shoot_clip.open(shoot_stream);
	    
	    hit_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("hit.wav"));
	    hit_format = hit_stream.getFormat();
	    hit_info= new DataLine.Info(Clip.class, hit_format);
	    hit_clip= (Clip) AudioSystem.getLine(hit_info);
	    hit_clip.open(hit_stream);
	    
	    catch_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("catch.wav"));
	    catch_format = catch_stream.getFormat();
	    catch_info= new DataLine.Info(Clip.class, catch_format);
	    catch_clip= (Clip) AudioSystem.getLine(catch_info);
	    catch_clip.open(catch_stream);
	    
	    win_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("win.wav"));
	    win_format = catch_stream.getFormat();
	    win_info= new DataLine.Info(Clip.class, win_format);
	    win_clip= (Clip) AudioSystem.getLine(win_info);
	    win_clip.open(win_stream);
	    
	    go_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("boo.wav"));
	    go_format = catch_stream.getFormat();
	    go_info= new DataLine.Info(Clip.class, go_format);
	    go_clip= (Clip) AudioSystem.getLine(go_info);
	    go_clip.open(go_stream);
	    
	    button_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("button-19.wav"));
	    button_format = catch_stream.getFormat();
	    button_info= new DataLine.Info(Clip.class, button_format);
	    button_clip= (Clip) AudioSystem.getLine(button_info);
	    button_clip.open(button_stream);
	    
	    enter_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("button-3.wav"));
	    enter_format = catch_stream.getFormat();
	    enter_info= new DataLine.Info(Clip.class, enter_format);
	    enter_clip= (Clip) AudioSystem.getLine(enter_info);
	    enter_clip.open(enter_stream);
	    
	    menu_stream=AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("loop_menu.wav"));
	    menu_format = catch_stream.getFormat();
	    menu_info= new DataLine.Info(Clip.class, menu_format);
	    menu_clip= (Clip) AudioSystem.getLine(menu_info);
	    menu_clip.open(menu_stream);
	    
	    
	}
	public void playMusic(){
					
			/* if(game_manager.gameOver())
			 {
				 if(fly_clip.isRunning())
					 fly_clip.stop();
				 if(loop_clip.isRunning())
					 loop_clip.stop();
			 }*/
			
			/* else if(!fly_clip.isActive())
	    	{
	    		
	    		loop_clip.stop();
	    		fly_clip.setMicrosecondPosition(0);
	    		fly_clip.start();
	    		fly_clip.loop(Clip.LOOP_CONTINUOUSLY);
	    	}*/
		    	
		    	
		    
		   /* else*/ if(!loop_clip.isActive())
	    	{
	    		
	    		//fly_clip.stop();
	    		loop_clip.setMicrosecondPosition(0);
	    		loop_clip.start();
	    		loop_clip.loop(Clip.LOOP_CONTINUOUSLY);
	    	}		    		    		    
	}
	public void playMenu()
	{
		if(!menu_clip.isActive())
    	{
    		
    		//fly_clip.stop();
    		menu_clip.setMicrosecondPosition(0);
    		menu_clip.start();
    		menu_clip.loop(Clip.LOOP_CONTINUOUSLY);
    	}		    		 
	}
	
	public void stopMenu()
	{
		if (menu_clip != null && menu_clip.isRunning()) {
		   menu_clip.stop();
		}
	}
	public void stopMusic()
	{
		loop_clip.stop();
	}
	
	public void playGameOver()
	{
		if(go_clip.isActive()) {
			go_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("GAME OVER");
		go_clip.setMicrosecondPosition(0);
		
		go_clip.drain();
		go_clip.start();
	}
	
	public void playButton19()
	{
		if(button_clip.isActive()) {
			button_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("PREMUTO");
		button_clip.setMicrosecondPosition(0);
		
		button_clip.drain();
		button_clip.start();
	}
	
	public void playButton3()
	{
		if(enter_clip.isActive()) {
			enter_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("PREMUTO");
		enter_clip.setMicrosecondPosition(0);
		
		enter_clip.drain();
		enter_clip.start();
	}
	
	public void playYouWin()
	{
		if(win_clip.isActive()) {
			win_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("PRESO");
		win_clip.setMicrosecondPosition(0);
		
		win_clip.drain();
		win_clip.start();
	}
	
	public void playCatch()
	{
		if(catch_clip.isActive()) {
			catch_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("PRESO");
		catch_clip.setMicrosecondPosition(0);
		
		catch_clip.drain();
		catch_clip.start();
	}
	
	public void playHit()
	{
		if(hit_clip.isActive()) {
			hit_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("COLPITO");
		hit_clip.setMicrosecondPosition(0);
		
		hit_clip.drain();
		hit_clip.start();
	}
	
	public void playFly()
	{
		if(fly_clip.isActive()) {
			fly_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("VOLO");
		fly_clip.setMicrosecondPosition(0);
		
		fly_clip.drain();
		fly_clip.start();
	}

	
		
	
	public void playShoot()
	{
		if(shoot_clip.isActive()){
			shoot_clip.stop();
			System.out.println("STOPPATO");
		}
		System.out.println("SPARATO");
		
		shoot_clip.setMicrosecondPosition(0);
		
		shoot_clip.drain();
		shoot_clip.start();
	}
}
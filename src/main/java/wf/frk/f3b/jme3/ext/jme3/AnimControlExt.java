package wf.frk.f3b.jme3.ext.jme3;

import java.lang.reflect.Field;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AnimControlExt{
	public static void setSkeleton(AnimControl ac,Skeleton sk){
		log.debug("Set skeleton {} to {}",sk,ac);
		Field field=null;
		
		// We can't rely on field name, or obfuscation will break the code.
		Field fields[]=AnimControl.class.getDeclaredFields();
		for(Field f:fields){
			if(f.getType()==Skeleton.class){
				field=f;
				break;
			}
		}
		
		if(field==null){
			log.error("Something went bad, can't find Skeleton field in AnimControl.class!");
			return;
		}
		field.setAccessible(true);
		try{
			field.set(ac,sk);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(sk!=null) sk.resetAndUpdate();
	}
}

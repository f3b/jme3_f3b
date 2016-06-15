package jme3_ext_xbuf.mergers.relations.linkers;
import static jme3_ext_xbuf.mergers.relations.LinkerHelpers.getRef1;
import static jme3_ext_xbuf.mergers.relations.LinkerHelpers.getRef2;

import java.util.Optional;

import org.slf4j.Logger;

import com.jme3.animation.SkeletonControl;
import com.jme3.material.Material;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;

import jme3_ext_xbuf.mergers.RelationsMerger;
import jme3_ext_xbuf.mergers.relations.Linker;
import jme3_ext_xbuf.mergers.relations.RefData;

public class MaterialToGeometry  implements Linker{

	@Override
	public boolean doLink(RelationsMerger loader, RefData data, Logger log) {
		Material op1=getRef1(data,Material.class,log);
		Geometry op2=getRef2(data,Geometry.class,log);
		if(op1==null||op2==null) return false;
		if(op2.getControl(SkeletonControl.class)!=null){
			op1=op1.clone();
			data.context.put("G~"+data.ref1+"~cloned~"+System.currentTimeMillis(),op1,data.ref1);
		}else{
			String refusage="G~usage~"+data.ref1;
			int n=(int)Optional.ofNullable(data.context.get(refusage)).orElse(0);
			data.context.put(refusage,n++);
		}

		op2.setMaterial(op1);
		Number bucket=((Number)data.context.get("G~"+data.ref1+"~RenderBucket"));
		if(bucket!=null){
			int b=bucket.intValue();
			switch(b){
				case 0: op2.setQueueBucket(Bucket.Opaque); break;
				case 1: op2.setQueueBucket(Bucket.Translucent); break;
				case 2: op2.setQueueBucket(Bucket.Transparent); break;
				case 3: op2.setQueueBucket(Bucket.Sky); break;
			}
		}
		
		return true;
	}
}

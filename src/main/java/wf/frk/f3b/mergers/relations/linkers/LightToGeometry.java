package wf.frk.f3b.mergers.relations.linkers;

import static wf.frk.f3b.mergers.relations.LinkerHelpers.getRef1;
import static wf.frk.f3b.mergers.relations.LinkerHelpers.getRef2;

import com.jme3.light.Light;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

import lombok.extern.log4j.Log4j2;
import wf.frk.f3b.mergers.RelationsMerger;
import wf.frk.f3b.mergers.relations.Linker;
import wf.frk.f3b.mergers.relations.RefData;
import wf.frk.f3b.scene.F3bLightControl;
@Log4j2
public class LightToGeometry implements Linker{

	@Override
	public boolean doLink(RelationsMerger loader, RefData data) {
		Light op1=getRef1(data,Light.class);
		Spatial op2=getRef2(data,Spatial.class);
		if(op1==null||op2==null) return false;
		if(op2 instanceof Geometry)	log.warn("Do you really want to add this light to a Geometry? [{}]",data.ref1);

		if(data.context.getSettings().useLightControls()){
			F3bLightControl f3blc=new F3bLightControl();
			f3blc.setLight(op1);
			op2.addControl(f3blc);
			data.root.addLight(op1);
		}else{
			op2.addLight(op1);
		}

		return true;
	}

}

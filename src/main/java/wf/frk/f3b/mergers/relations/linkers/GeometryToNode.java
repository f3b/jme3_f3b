package wf.frk.f3b.mergers.relations.linkers;
import static wf.frk.f3b.mergers.relations.LinkerHelpers.getRef1;
import static wf.frk.f3b.mergers.relations.LinkerHelpers.getRef2;

import org.slf4j.Logger;

import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import wf.frk.f3b.mergers.RelationsMerger;
import wf.frk.f3b.mergers.relations.Linker;
import wf.frk.f3b.mergers.relations.RefData;

public class GeometryToNode implements Linker{

	@Override
	public boolean doLink(RelationsMerger loader, RefData data, Logger log) {
		Geometry op1=getRef1(data,Geometry.class,log);
		Node op2=getRef2(data,Node.class,log);
		if(op1==null||op2==null) return false;

		// If already attached, clone geom, and add it to the context. Create also a link from the original one, this is used for other relations. (material for instance)
		if(op1.getParent()!=null){
			op1=op1.clone(false);
			data.context.put("G~"+data.ref1+"~cloned~"+System.currentTimeMillis(),op1,data.ref1);
		}
		op2.attachChild(op1);

		return true;
	}

}

package wf.frk.f3b.mergers.relations.linkers;

import static wf.frk.f3b.mergers.relations.LinkerHelpers.getRef1;
import static wf.frk.f3b.mergers.relations.LinkerHelpers.getRef2;

import org.slf4j.Logger;

import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import wf.frk.f3b.mergers.RelationsMerger;
import wf.frk.f3b.mergers.relations.Linker;
import wf.frk.f3b.mergers.relations.RefData;
import f3b.CustomParams.CustomParam;
import f3b.CustomParams.CustomParamList;

@ExtensionMethod({wf.frk.f3b.ext.PrimitiveExt.class})
@Slf4j
public class CustomParamToSpatial implements Linker{

	@Override
	public boolean doLink(RelationsMerger loader,RefData data, Logger log) {
		CustomParamList op1=getRef1(data,CustomParamList.class,log);
		Spatial op2=getRef2(data,Spatial.class,log);
		if(op1==null||op2==null) return false;
		for(CustomParam p:op1.getParamsList())merge(data.root,loader,p,op2);
		return true;
	}

	protected Spatial merge(Node root,RelationsMerger loader,CustomParam p, Spatial dst) {
		String name=p.getName();
		switch(p.getValueCase()){
			case VALUE_NOT_SET:
				dst.setUserData(name,null);
				break;
			case VBOOL:
				dst.setUserData(name,p.getVbool());
				break;
			case VCOLOR:
				dst.setUserData(name,p.getVcolor().toJME());
				break;
			case VFLOAT:
				dst.setUserData(name,p.getVfloat());
				break;
			case VINT:
				dst.setUserData(name,p.getVint());
				break;
			case VMAT4:
				dst.setUserData(name,p.getVmat4().toJME());
				break;
			case VQUAT:
				dst.setUserData(name,p.getVquat().toJME());
				break;
			case VSTRING:
				dst.setUserData(name,p.getVstring());
				break;
			case VVEC2:
				dst.setUserData(name,p.getVvec2().toJME());
				break;
			case VVEC3:
				dst.setUserData(name,p.getVvec3().toJME());
				break;
			case VVEC4:
				dst.setUserData(name,p.getVvec4().toJME());
				break;
			default:
				log.warn("Material doesn't support parameter : {} of type {}",name,p.getValueCase().name());
		}
		return dst;
	}
}

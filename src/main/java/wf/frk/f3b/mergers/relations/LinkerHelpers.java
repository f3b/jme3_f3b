package wf.frk.f3b.mergers.relations;


import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;

import wf.frk.f3b.scene.F3bMesh;

public class LinkerHelpers{
	public static <T> T getRef1(RefData data, Class<T> as) {
		return getRef(false,data,as);
	}

	public static <T> T getRef2(RefData data, Class<T> as) {
		return getRef(true,data,as);
	}

	@SuppressWarnings("unchecked")
	private static <T> T getRef(boolean id, RefData data, Class<T> as) {
		Object op1_o=data.context.get(!id?data.ref1:data.ref2);
		if(op1_o==null||!(as.isAssignableFrom(op1_o.getClass()))){
			// If we are picking a mesh as if it were a geometry, return a geometry automagically.
			if(op1_o instanceof F3bMesh&&(as.isAssignableFrom(Spatial.class)||as.isAssignableFrom(Geometry.class))){
				op1_o=getGeometry(id,data);
			}else op1_o=null;
		}
		return (T)op1_o;
	}

	public static Geometry getGeometry1(RefData data) {
		return getGeometry(false,data);
	}

	public static Geometry getGeometry2(RefData data) {
		return getGeometry(true,data);
	}

	private static Geometry getGeometry(boolean id, RefData data) {
		String ref=!id?data.ref1:data.ref2;
		// Generate geometry form mesh and keep it cached in the context
		Geometry geo=(Geometry)data.context.get("G~"+ref);
		if(geo==null){
			Object m=data.context.get(ref);
			if(m==null||!(m instanceof F3bMesh)) return null;

			F3bMesh f3bm=(F3bMesh)m;
			geo=new Geometry("",f3bm.toJME());
			geo.setName(f3bm.getName());
			geo.setMaterial(f3bm.material);

			data.context.put("G~"+ref,geo);
		}
		return geo;
	}
}

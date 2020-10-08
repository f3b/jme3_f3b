// Generated by delombok at Sat Jul 28 16:45:23 CEST 2018
package wf.frk.f3b.jme3.mergers;

import java.util.HashMap;
import wf.frk.f3banimation.Bone;
import wf.frk.f3banimation.Skeleton;
import com.jme3.math.Transform;
import com.jme3.scene.Node;
import f3b.Datas.Data;
import f3b.Relations.Relation;
import f3b.Skeletons;
import wf.frk.f3b.jme3.F3bContext;
import wf.frk.f3b.jme3.F3bKey;

public class SkeletonsMerger implements Merger {
	@Override
	public void apply(Data src, Node root, F3bKey key) {
		F3bContext context=key.getContext();
		for (f3b.Skeletons.Skeleton e : src.getSkeletonsList()) {
			// TODO manage parent hierarchy
			String id = e.getId();
			// TODO: merge with existing
			Skeleton child = makeSkeleton(e);
			context.put(id, child);
			// Skeleton child = (Skeleton)components.get(id);
		}
	}

	private Skeleton makeSkeleton(Skeletons.Skeleton e) {
		Bone[] bones = new Bone[e.getBonesCount()];
		HashMap<String, Bone> db = new HashMap<String, Bone>();
		for (int i = 0; i < bones.length; i++) {
			f3b.Skeletons.Bone src = e.getBones(i);
			Bone b = new Bone(src.getName());
			if(src.hasLength())b.setLength(src.getLength());
			Transform tr=new Transform(wf.frk.f3b.jme3.ext.f3b.TypesExt.toJME(src.getTranslation()), 
			wf.frk.f3b.jme3.ext.f3b.TypesExt.toJME(src.getRotation()), 
			wf.frk.f3b.jme3.ext.f3b.TypesExt.toJME(src.getScale()));
			b.setRestTransform(tr);
			db.put(src.getId(), b);
			bones[i] = b;
		}
		for (Relation r : e.getBonesGraphList()) {
			Bone parent = db.get(r.getRef1());
			Bone child = db.get(r.getRef2());
			parent.addChild(child);
		}
		Skeleton sk = new Skeleton(bones);
		sk.updateRestPose();

		return sk;
	}
}

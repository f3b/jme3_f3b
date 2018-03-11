package wf.frk.f3b.jme3;

import java.io.IOException;
import java.io.InputStream;

import com.google.protobuf.CodedInputStream;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

import f3b.Datas.Data;

public class F3bLoader implements AssetLoader {
	public static void init(AssetManager am){
		am.registerLoader(F3bLoader.class,"f3b");
	}
	
	public F3b buildF3b(AssetInfo assetInfo){
		return  new F3b(assetInfo.getManager());

	}
	
	@Override
	public Object load(AssetInfo assetInfo) throws IOException {

		Node root = new Node(assetInfo.getKey().getName());
		InputStream in = null ;
		CodedInputStream cin;
		try {
			F3bKey f3bkey=null;
			AssetKey<?> key=assetInfo.getKey();
			if(key instanceof F3bKey){
				f3bkey=(F3bKey)key;
			}else{
				f3bkey=new F3bKey(key.getName());
			}
			in=assetInfo.openStream();
			F3b f3b=buildF3b(assetInfo);
			cin=CodedInputStream.newInstance(in);
			cin.setSizeLimit(Integer.MAX_VALUE);
			Data src=Data.parseFrom(cin,f3b.extensions);
			F3bContext context=new F3bContext();
			context.setSettings(f3bkey);
			f3b.merge(f3bkey.executor(),src, root, context);	
		} finally {
			if(in!=null)in.close();
		}
		return root;
	}}

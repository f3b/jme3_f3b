// Generated by delombok at Sat Jul 28 16:45:23 CEST 2018
package wf.frk.f3b.jme3.animations;

import java.util.LinkedList;
import java.util.List;

import wf.frk.f3banimation.Animation;
import wf.frk.f3banimation.Skeleton;

public class F3bAnimation {
	protected final String name;
	protected final float duration;
	protected final List<F3bAnimTrack> tracks = new LinkedList<F3bAnimTrack>();
	protected final int priority;

	public Animation toJME(Skeleton sk) {
		Animation anim = new Animation(getName(), getDuration());
		System.out.println("Load anim "+getName());
		for (F3bAnimTrack t : tracks) anim.addTrack(t.toJME(sk));
		anim.setPriority(priority);
		return anim;
	}

	@java.lang.SuppressWarnings("all")
	public F3bAnimation(final String name, final float duration,final int priority) {
		this.name = name;
		this.duration = duration;
		this.priority=priority;
	}

	@java.lang.SuppressWarnings("all")
	public String getName() {
		return this.name;
	}

	@java.lang.SuppressWarnings("all")
	public float getDuration() {
		return this.duration;
	}

	@java.lang.SuppressWarnings("all")
	public List<F3bAnimTrack> getTracks() {
		return this.tracks;
	}

	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public boolean equals(final java.lang.Object o) {
		if (o == this) return true;
		if (!(o instanceof F3bAnimation)) return false;
		final F3bAnimation other = (F3bAnimation) o;
		if (!other.canEqual((java.lang.Object) this)) return false;
		final java.lang.Object this$name = this.getName();
		final java.lang.Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
		if (java.lang.Float.compare(this.getDuration(), other.getDuration()) != 0) return false;
		final java.lang.Object this$tracks = this.getTracks();
		final java.lang.Object other$tracks = other.getTracks();
		if (this$tracks == null ? other$tracks != null : !this$tracks.equals(other$tracks)) return false;
		return true;
	}

	@java.lang.SuppressWarnings("all")
	protected boolean canEqual(final java.lang.Object other) {
		return other instanceof F3bAnimation;
	}

	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final java.lang.Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		result = result * PRIME + java.lang.Float.floatToIntBits(this.getDuration());
		final java.lang.Object $tracks = this.getTracks();
		result = result * PRIME + ($tracks == null ? 43 : $tracks.hashCode());
		return result;
	}

	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public java.lang.String toString() {
		return "F3bAnimation(name=" + this.getName() + ", duration=" + this.getDuration() + ", tracks=" + this.getTracks() + ")";
	}
}

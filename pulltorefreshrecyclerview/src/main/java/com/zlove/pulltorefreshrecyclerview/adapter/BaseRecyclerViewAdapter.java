package com.zlove.pulltorefreshrecyclerview.adapter;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.zlove.pulltorefreshrecyclerview.animation.AlphaInAnimation;
import com.zlove.pulltorefreshrecyclerview.animation.BaseAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZLOVE on 2016/11/2.
 */
public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements DataIO<T> {

    protected Context mContext;
    protected List<T> data;

    //动画
    private int mLastPosition = -1;
    private boolean mOpenAnimationEnable = true;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mDuration = 300;
    private BaseAnimation mSelectAnimation = new AlphaInAnimation();

    public BaseRecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
        data = new ArrayList<>();
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    /**
     * 添加动画
     *
     * @param holder
     */
    public void addAnimation(RecyclerView.ViewHolder holder) {
        if (mOpenAnimationEnable) {
            if (holder.getLayoutPosition() > mLastPosition) {
                BaseAnimation animation = null;
                if (mSelectAnimation != null) {
                    animation = mSelectAnimation;
                }
                for (Animator anim : animation.getAnimators(holder.itemView)) {
                    startAnim(anim, holder.getLayoutPosition());
                }
                mLastPosition = holder.getLayoutPosition();
            }
        }
    }
    /**
     * 开始动画
     *
     * @param anim
     * @param index
     */
    protected void startAnim(Animator anim, int index) {
        anim.setDuration(mDuration).start();
        anim.setInterpolator(mInterpolator);
    }

    /**
     * 设置动画
     *
     * @param animation ObjectAnimator
     */
    public void openLoadAnimation(BaseAnimation animation) {
        this.mOpenAnimationEnable = true;
        this.mSelectAnimation = animation;
    }

    /**
     * 关闭动画
     */
    public void closeLoadAnimation() {
        this.mOpenAnimationEnable = false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        addAnimation(holder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void add(T elem) {
        if (elem == null) {
            return;
        }
        data.add(elem);
        notifyItemChanged(data.size() - 1);
    }

    @Override
    public void addAt(int location, T elem) {
        data.add(location, elem);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<T> elements) {
        data.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void addAllAt(int location, List<T> elements) {
        data.addAll(location, elements);
        notifyDataSetChanged();
    }

    @Override
    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    @Override
    public void removeAll(List<T> elements) {
        data.removeAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public void removeAt(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        if (data != null && data.size() > 0) {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void replace(T oldElem, T newElem) {
            replaceAt(data.indexOf(oldElem), newElem);
    }

    @Override
    public void replaceAt(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    @Override
    public void replaceAll(List<T> elements) {
        if (data.size() > 0) {
            data.clear();
        }
        data.addAll(elements);
        notifyDataSetChanged();
    }

    @Override
    public List<T> getAll() {
        return data;
    }

    @Override
    public T get(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean contains(T elem) {
        return data.contains(elem);
    }
}

package net.brian.italker.common.widget.recycler;

/**
 * @author brian
 */

public interface AdapterCallback<Data> {
    void update(Data data,RecyclerAdapter.ViewHolder<Data> holder);
}

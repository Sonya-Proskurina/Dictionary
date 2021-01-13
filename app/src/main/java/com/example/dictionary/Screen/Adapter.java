package com.example.dictionary.Screen;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.dictionary.DataBase.Dictionary;
import com.example.dictionary.R;
import com.example.dictionary.Screen.Screens.EditionActivity;
import com.example.dictionary.Screen.Screens.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Adapter extends RecyclerView.Adapter<Adapter.DictionaryViewHolder> {

    public static SortedList<Dictionary> sortedList;

    public Adapter() {

        sortedList = new SortedList<>(Dictionary.class, new SortedList.Callback<Dictionary>() {
            @Override
            public int compare(Dictionary o1, Dictionary o2) {
                return o1.engWord.compareTo(o2.engWord);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean areContentsTheSame(Dictionary oldItem, Dictionary newItem) {

                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Dictionary item1, Dictionary item2) {
                return item1.id == item2.id;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);

            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DictionaryViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
        holder.bind(sortedList.get(position));

    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Dictionary> dictionaryList) {
        sortedList.replaceAll(dictionaryList);

    }

    static class DictionaryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_eng_item)
        TextView textViewE;
        @BindView(R.id.text_rus_item)
        TextView textViewR;

       private Dictionary dictionary;

        public DictionaryViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditionActivity.start((Activity) itemView.getContext(), dictionary);
                }
            });
        }

        @OnClick(R.id.button_delete)
        public void delete(){
            MainActivity.deleteThread(dictionary);
        }

        public void bind(Dictionary dictionary) {
            this.dictionary = dictionary;
            textViewE.setText(dictionary.engWord);
            textViewR.setText(dictionary.rusWord);
        }
    }
}

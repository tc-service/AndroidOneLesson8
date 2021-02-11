package com.technocopy.androidonelesson8.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.technocopy.androidonelesson8.R;
import com.technocopy.androidonelesson8.data.CardData;
import com.technocopy.androidonelesson8.data.CardsSource;

import java.text.SimpleDateFormat;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.MyViewHolder> {

    private final static String TAG = "MyNotesAdapter";
    private CardsSource dataSource;
    private final Fragment fragment;

    private MyClickListener myClickListener;
    private int menuPosition;

    public MyNoteAdapter(Fragment fragment){
//        this.dataSource = dataSource;
        this.fragment = fragment;
    }
    public void setDataSource(CardsSource dataSource){
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyNoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_item, viewGroup, false);

        Log.d(TAG, "onCreateViewHolder");
        // Здесь можно установить всякие параметры
        return new MyViewHolder(v);
    }

    //передаем данные в созданный ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyNoteAdapter.MyViewHolder viewHolder, int i) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран, используя ViewHolder
        viewHolder.setData(dataSource.getCardData(i));
        Log.d(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
//        return dataSource.length;
        return dataSource.size();
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(MyClickListener itemClickListener){
        myClickListener = itemClickListener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    // Интерфейс для обработки нажатий, как в ListView
    public interface MyClickListener {
        void onItemClick(View view , int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;
        private TextView date;

        public MyViewHolder(@NonNull final View itemView) {

            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
            date = itemView.findViewById(R.id.date);

            registerContextMenu(itemView);

            // Обработчик нажатий на этом ViewHolder
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myClickListener != null) {
                        myClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            // Обработчик долгих нажатий на картинке
            image.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(10, 10);
                    return true;
                }
            });
        }

        private void registerContextMenu(@NonNull View itemView) {
            if (fragment != null){
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        menuPosition = getLayoutPosition();
                        return false;
                    }
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void setData(CardData cardData){
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
            like.setChecked(cardData.isLike());
            image.setImageResource(cardData.getPicture());
            date.setText(new SimpleDateFormat("dd-MM-yy").format(cardData.getDate()));
        }
    }
}
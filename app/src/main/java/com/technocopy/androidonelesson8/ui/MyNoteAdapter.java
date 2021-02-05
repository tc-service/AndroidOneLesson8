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
import androidx.recyclerview.widget.RecyclerView;

import com.technocopy.androidonelesson8.R;
import com.technocopy.androidonelesson8.data.CardData;
import com.technocopy.androidonelesson8.data.CardsSource;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.MyViewHolder> {

    private final static String TAG = "SocialNetworkAdapter";

//    private String[] dataSource;
    private CardsSource dataSource;

    private MyClickListener myClickListener;

//    private AdapterView.OnItemClickListener itemClickListener;  // Слушатель будет устанавливаться извне

    public MyNoteAdapter(CardsSource dataSource){
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MyNoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
//        return new MyViewHolder(v);
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
//        viewHolder.getTextView().setText(dataSource[i]);
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

    // Интерфейс для обработки нажатий, как в ListView
    public interface MyClickListener {
        void onItemClick(View view , int position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

//        private TextView textView;
//        private ImageView imageView;
        private TextView title;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
//            textView = itemView.findViewById(R.id.tvNote);
//            imageView = itemView.findViewById(R.id.imNoteName);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);


//            textView = (TextView)itemView;
//            imageView = (ImageView) itemView;

            // Обработчик нажатий на этом ViewHolder
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myClickListener != null) {
                        myClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
        public void setData(CardData cardData){
            title.setText(cardData.getTitle());
            description.setText(cardData.getDescription());
            like.setChecked(cardData.isLike());
            image.setImageResource(cardData.getPicture());
        }


//public TextView getTextView() {
//    return textView;
//}
    }
}
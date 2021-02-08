package com.technocopy.androidonelesson8.ui;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.technocopy.androidonelesson8.R;
import com.technocopy.androidonelesson8.data.CardData;
import com.technocopy.androidonelesson8.data.CardsSource;
import com.technocopy.androidonelesson8.data.CardsSourceImpl;
import com.technocopy.androidonelesson8.ui.MyNoteAdapter;


public class MyNotesFragment extends Fragment {

    private CardsSource data;
    private MyNoteAdapter adapter;
    private RecyclerView recyclerView;

    public static MyNotesFragment newInstance() {
        return new MyNotesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_notes, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
//        String[] data = getResources().getStringArray(R.array.titles);

        // Получим источник данных для списка
         data = new CardsSourceImpl(getResources()).init();
//        initRecyclerView(recyclerView, data);

        initView(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.cards_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                data.addCardData(new CardData("Заголовок " + data.size(),
                        "Описание " + data.size(),
                        R.drawable.petropavl_krep,
                        false));
                adapter.notifyItemInserted(data.size() - 1);
                recyclerView.scrollToPosition(data.size() - 1);
                return true;
            case R.id.action_clear:
                data.clearCardData();
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        // Получим источник данных для списка
        data = new CardsSourceImpl(getResources()).init();
        initRecyclerView();
    }


    private void initRecyclerView(){

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
       adapter = new MyNoteAdapter(data, this);
        recyclerView.setAdapter(adapter);

        // Установим слушателя
        adapter.SetOnItemClickListener(new MyNoteAdapter.MyClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), String.format("%s - %d", ((TextView)view).getText(), position), Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_update:
                // Do some stuff
                return true;
            case R.id.action_delete:
                // Do some stuff
                return true;
        }
        return super.onContextItemSelected(item);
    }
}

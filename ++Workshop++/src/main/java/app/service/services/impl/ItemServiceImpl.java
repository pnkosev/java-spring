package app.service.services.impl;

import app.data.models.Item;
import app.data.repositories.ItemRepository;
import app.service.models.item.ItemCreateServiceModel;
import app.service.services.api.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(ItemCreateServiceModel itemServiceModel) {
        this.itemRepository
                .save(this.modelMapper.map(itemServiceModel, Item.class));
    }
}

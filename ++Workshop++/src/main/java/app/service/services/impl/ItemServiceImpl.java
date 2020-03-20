package app.service.services.impl;

import app.data.models.Item;
import app.data.repositories.ItemRepository;
import app.service.models.item.ItemCreateServiceModel;
import app.service.models.item.ItemServiceModel;
import app.service.services.api.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ItemServiceModel> getAllItems(String heroName) {
        return this.itemRepository
                .findAll()
                .stream()
                .map(i -> {
                    ItemServiceModel ism = this.modelMapper.map(i, ItemServiceModel.class);

                    i.getHeroes().forEach(h -> {
                        if (h.getName().equals(heroName)) {
                            ism.setOwned(true);
                        }
                    });
                    return ism;
                })
                .collect(Collectors.toList());
    }
}

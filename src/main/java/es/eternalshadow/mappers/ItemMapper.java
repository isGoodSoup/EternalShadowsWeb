package es.eternalshadow.mappers;

import es.eternalshadow.dto.ItemDTO;
import es.eternalshadow.entities.Item;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        return new ItemDTO(item.getNombre(), item.getCantidad());
    }

    public static Item toEntity(ItemDTO dto) {
        return new Item(dto.getNombre(), dto.getCantidad());
    }
}


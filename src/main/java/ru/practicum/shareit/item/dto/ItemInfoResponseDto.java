package ru.practicum.shareit.item.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemInfoResponseDto {
    private long id;
    private String name;
    private String description;
    private boolean available;
}
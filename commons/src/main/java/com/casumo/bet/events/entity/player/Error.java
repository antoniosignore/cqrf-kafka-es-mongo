package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Error {
    public String type;
    public List<String> arguments = null;
}
package br.com.harrypotter.makemagic.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Lucas de Oliveira
 * @since 14/09/2020
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CharacterHouseTO implements Serializable {

    private static final long serialVersionUID = 1170869603693312975L;

    private String _id;
    private String name;
    private String mascot;
    private String headOfHouse;
    private String houseGhost;
    private String founder;
    private String __v;
    private String school;
}
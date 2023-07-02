package com.eleks.auctioneer.DTO;


import com.eleks.auctioneer.entity.Lot;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotDTO {

    private Long id_lot;
    @NotBlank(message = "Name of lot is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name_of_lot;
    private String author;
    @NotEmpty
    private String category;
    @NotEmpty
    private String description;
    @NotEmpty
    private Timestamp expiration;


    public static Lot mapToLot(LotDTO lotdto){
        Lot lot = new Lot();
        lot.setId_lot(lotdto.getId_lot());
        lot.setName_of_lot(lotdto.getName_of_lot());
        lot.setAuthor(lotdto.getAuthor());
        lot.setCategory(lotdto.getCategory());
        lot.setDescription(lotdto.getDescription());
        lot.setExpiration(lotdto.getExpiration());

        return lot;
    }
}

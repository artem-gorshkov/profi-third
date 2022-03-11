package gorshkov.profi.software.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Promo {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany
    @JoinColumn(name = "promo_id")
    private List<Prize> prizes = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "promo_id")
    private List<Participant> participants = new ArrayList<>();

}

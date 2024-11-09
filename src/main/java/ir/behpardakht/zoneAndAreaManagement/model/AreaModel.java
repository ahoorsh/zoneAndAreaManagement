package ir.behpardakht.zoneAndAreaManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AREA")
public class AreaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AREA_ID")
    private Long id;

    private String name;

    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ZoneModel> zoneModels;
}

package cz.gyarab3e.rocnikovaprace3.jpa;

import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@TypeDef(
        name = "cell_status_array",
        typeClass = EnumArrayType.class
)
public class Game extends BaseGame{
    @ManyToOne
    private GameUser user1;

    @ManyToOne
    private GameUser user2;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Type(
            type = "cell_status_array",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "sql_array_type",
                    value = "cell_status"
            )
    )
    @Column(
            name = "cell_grid1",
            columnDefinition = "cell_status[][]"
    )
    private CellStatus[][] cellStatuses1;
    @Type(
            type = "cell_status_array",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "sql_array_type",
                    value = "cell_status"
            )
    )
    @Column(
            name = "cell_grid2",
            columnDefinition = "cell_status[][]"
    )
    private CellStatus[][] cellStatuses2;

    public GameUser getUser1() {
        return user1;
    }

    public void setUser1(GameUser user1) {
        this.user1 = user1;
    }

    public GameUser getUser2() {
        return user2;
    }

    public void setUser2(GameUser user2) {
        this.user2 = user2;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public CellStatus[][] getCellStatuses1() {
        return cellStatuses1;
    }

    public void setCellStatuses1(CellStatus[][] cellStatuses1) {
        this.cellStatuses1 = cellStatuses1;
    }

    public CellStatus[][] getCellStatuses2() {
        return cellStatuses2;
    }

    public void setCellStatuses2(CellStatus[][] cellStatuses2) {
        this.cellStatuses2 = cellStatuses2;
    }
}


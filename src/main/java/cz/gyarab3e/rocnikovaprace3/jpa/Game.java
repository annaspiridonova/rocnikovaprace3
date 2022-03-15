package cz.gyarab3e.rocnikovaprace3.jpa;

import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import cz.gyarab3e.rocnikovaprace3.controller.MoveStatus;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

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

    private Date updatedate;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private Integer lastX;
    private Integer lastY;
    private MoveStatus lastMoveStatus;

    public Integer getLastX() {
        return lastX;
    }

    public void setLastX(Integer lastX) {
        this.lastX = lastX;
    }

    public Integer getLastY() {
        return lastY;
    }

    public void setLastY(Integer lastY) {
        this.lastY = lastY;
    }

    public MoveStatus getLastMoveStatus() {
        return lastMoveStatus;
    }

    public void setLastMoveStatus(MoveStatus lastMoveStatus) {
        this.lastMoveStatus = lastMoveStatus;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

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

    public GameUser getUserByName(String name){
        if(user1.getUsername().equals(name)){
            return user1;
        }
        else if(user2.getUsername().equals(name)){
            return user2;
        }else{
            return null;
        }
    }

}


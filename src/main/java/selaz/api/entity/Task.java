package selaz.api.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "`task`")  
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at") 
    private Date createdAt;

    @Column(name = "due_date") 
    private Date dueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`status`")
    private Status status; 

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`user`", referencedColumnName = "id")
    private User user;

}

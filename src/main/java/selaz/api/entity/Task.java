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
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "`task`")  
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @NotBlank
    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`status`")
    private Status status; 

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`user`", referencedColumnName = "id")
    private User user;

}

package mainpackage.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String originalFileName;
    private Long size;//размер
    private String contentType;//расширение файла
    private boolean isPreviewImage;//фото для главного отображения
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH)
    Product product;
}

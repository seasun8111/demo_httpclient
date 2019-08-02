package com.luckybees.demo_mysql.po;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="beichen_id_mapping")
@Table(name = "beichen_id_mapping",
        indexes = {
                @Index(name="vId_index",
                        columnList = "vId",
                        unique = false),
                @Index(name="vKey_index",
                        columnList = "vKey",
                        unique = false),
                @Index(name="type_index",
                        columnList = "type")}
        )
@Data
@NoArgsConstructor
@IdClass(IdMappingIdClass.class)
public class IdMapping implements Serializable {






    @Id
    @Column(length = 16, nullable = false)
    String vId;
    @Id
    @Column(length = 16, nullable = false)
    String vKey;
    @Id
    @Column(length = 16, nullable = false)
    String type;

}

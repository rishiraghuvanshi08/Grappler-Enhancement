package com.grapplermodule1.GrapplerEnhancement.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HierarchyUpdate {

  @NotNull
  private Long draggedId;
  @NotNull
  private Long droppedId;

}

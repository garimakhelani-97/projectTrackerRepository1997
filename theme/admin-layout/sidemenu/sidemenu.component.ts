import { Component, Input, OnInit } from '@angular/core';
import { MenuService } from '@core';

@Component({
  selector: 'app-sidemenu',
  templateUrl: './sidemenu.component.html',
})
export class SidemenuComponent implements OnInit {
  // NOTE: Ripple effect make page flashing on mobile
  @Input() ripple = true;

  menus;

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.menuService.menuChanges$.subscribe(menus => {
      this.menus = menus;
    });
  }

  // Delete empty value in array
  filterStates(states: string[]) {
    return states.filter(item => item && item.trim());
  }
}

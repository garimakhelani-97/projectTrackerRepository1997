import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { state } from '@angular/animations';

export interface Tag {
  color: string; // Background Color
  value: string;
}

export interface ChildrenItem {
  uri: string;
  subMenuName: string;
  subMenuType: 'link' | 'sub' | 'extLink' | 'extTabLink';
  orderId: number;
  children?: ChildrenItem[];
}

export interface Menu {
  uri: string;
  menuName: string;
  menuType: 'link' | 'sub' | 'extLink' | 'extTabLink';
  menuKey: string;
  orderId: number;
  label?: Tag;
  badge?: Tag;
  children?: ChildrenItem[];
}

@Injectable({
  providedIn: 'root',
})
export class MenuService {
  private menu: Menu[] = [];

  private menuChangesSource = new BehaviorSubject<Menu[]>([]);
  menuChanges$ = this.menuChangesSource.asObservable();

  getAll(): Menu[] {
    return this.menu;
  }

  set(menu: Menu[]) {
    this.menu = [];
    this.menu = this.menu.concat(menu);
    this.menuChangesSource.next(this.menu);
    // return this.menu;
  }

  add(menu: Menu) {
    this.menu.push(menu);
  }

  getMenuItemName(stateArr: string[]): string {
    return this.getMenuLevel(stateArr)[stateArr.length - 1];
  }

  // TODO:
  getMenuLevel(stateArr: string[]): string[] {
    const tempArr = [];
    if (stateArr[0] === 'home') {
      tempArr.push(stateArr[0]);
    }
    this.menu.map(item => {
      if (item.uri === stateArr[1]) {
        tempArr.push(item.menuName);
        // Level1
        if (item.children && item.children.length) {
          item.children.forEach(itemlvl1 => {
            if (stateArr[2] && itemlvl1.uri === stateArr[2]) {
              tempArr.push(itemlvl1.subMenuName);
              // Level2
              if (itemlvl1.children && itemlvl1.children.length) {
                itemlvl1.children.forEach(itemlvl2 => {
                  if (stateArr[3] && itemlvl2.uri === stateArr[3]) {
                    tempArr.push(itemlvl2.subMenuName);
                  }
                });
              }
            } else if (stateArr[2]) {
              // Level2
              if (itemlvl1.children && itemlvl1.children.length) {
                itemlvl1.children.forEach(itemlvl2 => {
                  if (itemlvl2.uri === stateArr[2]) {
                    tempArr.push(itemlvl1.subMenuName, itemlvl2.subMenuName);
                  }
                });
              }
            }
          });
        }
      }
    });
    return tempArr;
  }
}

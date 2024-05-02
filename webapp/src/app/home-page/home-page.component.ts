import { PostService } from './../shared/services/post.service';
import { Component, OnInit } from '@angular/core';
import { PostModel } from '../shared/model/post.model';
import { UserLoginService } from '../shared/services/user-login.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  public posts: Array<PostModel> = new Array<PostModel>();
  constructor(private postService: PostService, public userService:UserLoginService) { }

  ngOnInit(): void {
    this.postService.getPosts().subscribe(
      (data: Array<PostModel>) => {
        console.log(JSON.stringify(data));
        this.posts = data;
      },
      (error) => {
        console.log(error);
        throw error;
      }
    );
  }

}
